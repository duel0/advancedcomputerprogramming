import multiprocess as mp

class MyProcess(mp.Process):
    def run(self):
        print("Process running")
        return
if __name__ == "__main__":
    p = MyProcess()

    p.start()

    p.join()